package id.asqi.idesa.bumdes.core.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import id.asqi.idesa.bumdes.core.http.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class Response<D> {
    private Integer code = 0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object validation;


    /*pagination*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer showing;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer currentPage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private D data;

    public void setResponseCode (ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public void setData (D data) {
        if (data instanceof List<?>) {
            this.total = (long) ((List<?>) data).size();
        }
        this.data = data;
    }

    public void setData (Page<?> pageData, Object dataDtoList) {
        this.data = (D) dataDtoList;
        this.total = pageData.getTotalElements();
        this.showing = pageData.getNumberOfElements();
        this.totalPages = pageData.getTotalPages();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
    }

    public void setPageData (Page<?> pageData) {
        this.data = (D) pageData.getContent();
        this.total = pageData.getTotalElements();
        this.showing = pageData.getNumberOfElements();
        this.totalPages = pageData.getTotalPages();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;

    }


}