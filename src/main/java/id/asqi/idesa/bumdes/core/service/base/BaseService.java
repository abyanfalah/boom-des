package id.asqi.idesa.bumdes.core.service.base;

import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*still in progres.*/

public abstract class BaseService<T, ID> {
	public final JpaRepository<T, ID> repository;
	public final Class<T> entityClass;

	public BaseService (JpaRepository<T, ID> repository, Class<T> entityClass) {
		this.repository = repository;
		this.entityClass = entityClass;
	}

	public BaseService () {
		this.repository = null;
		this.entityClass = null;
	}

	public T findById (ID id) {
		assert repository != null;
		assert entityClass != null;

		return repository.findById(id).orElseThrow(() -> new NotFoundEntity(entityClass));
	}
}