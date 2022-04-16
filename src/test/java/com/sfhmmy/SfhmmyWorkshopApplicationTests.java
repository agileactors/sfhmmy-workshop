package com.sfhmmy;

import com.sfhmmy.dto.CreateUserRequestDto;
import com.sfhmmy.dto.CreateUserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("FT")
class SfhmmyWorkshopApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void hello() {
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/users", HttpMethod.GET, null, String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void createUser() {
		CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
		createUserRequestDto.setEmail("test@test.com");
		createUserRequestDto.setFirstName("testname");
		createUserRequestDto.setLastName("testlastname");
		HttpEntity<CreateUserRequestDto> httpEntity = new HttpEntity<>(createUserRequestDto);
		ResponseEntity<CreateUserResponseDto> responseEntity =
				testRestTemplate.exchange("/users", HttpMethod.POST, httpEntity, CreateUserResponseDto.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

		CreateUserResponseDto createUserResponseDto = responseEntity.getBody();
		assertThat(createUserResponseDto.getId()).isNotNull();
		assertThat(createUserResponseDto.getEmail()).isEqualTo("test@test.com");
		assertThat(createUserResponseDto.getFirstName()).isEqualTo("testname");
		assertThat(createUserResponseDto.getLastName()).isEqualTo("testlastname");
	}

}
