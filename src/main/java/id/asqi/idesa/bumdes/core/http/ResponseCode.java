package id.asqi.idesa.bumdes.core.http;

import lombok.Getter;

@Getter
public enum ResponseCode {
    NO_END_POINT(404, "ENDPOINT NOT FOUND"),
    SUCCESS(200, "OK"),
    SERVER_ERROR(500, "INTERNAL SERVER ERROR"),
    DATABASE_ERROR(501, "INTERNAL SERVER ERROR"),
    VALIDATION(100, "ERROR MAPPING"),
    TypeMismatch(103, "ERROR MAPPING"),
    OTP(102, "Error OTP"),
    JWTExp(401, "Token is invalid or expired"),
    UNAUTHORIZED(402, "Token Invalid"),
    BAD_REQUEST(400, "BAD REQUEST"),
    NO_DATA(101, "DATA TIDAK DITEMUKAN"),
    //==============
    FORBIDDEN(403, "FORBIDDEN");

    private final Integer code;
    private final String message;

    ResponseCode (Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}