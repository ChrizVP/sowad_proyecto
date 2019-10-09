package com.proyecto.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.models.Cliente;

public abstract class GenericService <T, ID extends Serializable> implements Generic<T, ID>{

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void deleted(ID id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getDao().findById(id);
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		getDao().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}
	
	
	
	
	public abstract CrudRepository<T, ID> getDao();
	
}
