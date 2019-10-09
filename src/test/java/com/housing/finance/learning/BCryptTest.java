package com.housing.finance.learning;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.assertj.core.api.Assertions.assertThat;

public class BCryptTest {

    @Test
    public void testEncodingHashPwBySamePw() {
        String pw = "test";
        String hashPwOne = BCrypt.hashpw(pw, BCrypt.gensalt());
        String hashPwTwo = BCrypt.hashpw(pw, BCrypt.gensalt());

        assertThat(hashPwOne).isNotEqualTo(hashPwTwo);
    }

    @Test
    public void testCheckHashPw() {
        String pw = "test";
        String hashPwOne = BCrypt.hashpw(pw, BCrypt.gensalt());

        assertThat(BCrypt.checkpw(pw, hashPwOne)).isTrue();
    }
}
