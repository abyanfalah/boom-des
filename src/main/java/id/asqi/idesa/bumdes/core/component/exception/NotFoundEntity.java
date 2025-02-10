package id.asqi.idesa.bumdes.core.component.exception;

import id.asqi.idesa.bumdes.core.Constants;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundEntity extends RuntimeException{

    public NotFoundEntity(String resourceName){
        super(resourceName.concat(" tidak ditemukan"));
    }

    public <T> NotFoundEntity(Class<T> tClass){
        super(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" tidak ditemukan"));
    }

    public NotFoundEntity(String resourceName, String additionalMessage){
        super(resourceName.concat("tidak ditemukan. ".concat(additionalMessage)));
    }

    public NotFoundEntity(){
        super("Resource tidak ditemukan");
    }
}