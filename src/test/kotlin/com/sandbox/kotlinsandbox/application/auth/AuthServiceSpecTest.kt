package com.sandbox.kotlinsandbox.application.auth

import com.sandbox.kotlinsandbox.application.auth.service.AuthService
import com.sandbox.kotlinsandbox.application.user.domain.User
import com.sandbox.kotlinsandbox.application.user.infrastructure.UserRepository
import com.sandbox.kotlinsandbox.web.auth.dto.AuthDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional


@Transactional
@SpringBootTest
internal class AuthServiceSpecTest : BehaviorSpec() {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    override fun extensions() = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))

    init {
        beforeSpec {
            println("테스트 시작 확인")
        }

        given("회원가입을 시도할 경우") {
            `when`("이미 존재하는 이메일로 가입 시") {
                val input = createJoinRequest()
                userRepository.save(
                    User(
                        email = input.email,
                        name = input.name,
                        phone = input.phone,
                        password = encoder.encode(input.password)
                    )
                )
                then("중복으로 인한 예외처리한다") {

                    val exception = shouldThrow<IllegalArgumentException> {
                        authService.join(input)
                    }
                    exception.message should startWith("이미 존재하는 계정입니다.")
                }
            }

            `when`("신규 가입 시") {
                val newInput = createJoinRequest(email = "other@gmail.com")
                then(" 유저 정보를 응답한다") {
                    authService.join(newInput)
                }
            }
        }

        given("로그인 시도 시") {
            val input = createLoginRequest()
            val join = createJoinRequest()
            userRepository.save(
                User(
                    email = input.email,
                    name = join.name,
                    phone = join.phone,
                    password = encoder.encode(join.password)
                )
            )

            `when`("(success) 올바른 이메일과 패스워드 입력 시") {
                then("정상 로그인 및 토큰을 응답한다") {
                    authService.login(input)
                }
            }

            `when`("(fail) 존재하지 않는 이메일로 입력 시") {
                val newInput = createLoginRequest(email = "other@gmail.com")
                then("로그인 실패 예외 처리한다") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        authService.login(newInput)
                    }
                    exception.message shouldBe "아이디 또는 비밀번호가 일치하지 않습니다."
                }
            }

            `when`("(fail) 잘못된 패스워드로 입력 시") {
                val newInput = createLoginRequest(password = "91919191919191")
                then("로그인 실패 예외 처리한다") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        authService.login(newInput)
                    }
                    exception.message shouldBe "아이디 또는 비밀번호가 일치하지 않습니다."
                }
            }
        }
    }

}

private fun createJoinRequest(
    email: String = "test@test.com",
    name: String = "홍길동",
    password: String = "123",
    phone: String = "01010041004"

): AuthDto.JoinRequest {
    return AuthDto.JoinRequest(
        email = email, name = name, password = password, phone = phone
    )
}

private fun createLoginRequest(
    email: String = "test@test.com",
    password: String = "123",
) = AuthDto.LoginRequest(email = email, password = password)