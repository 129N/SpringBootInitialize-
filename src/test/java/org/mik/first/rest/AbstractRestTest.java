package org.mik.first.rest;

import org.junit.jupiter.api.Assertions;
import org.mik.first.AbstractTest;

import org.mik.first.domain.AbstractDomain;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public class AbstractRestTest<ID extends Serializable, E extends AbstractDomain<ID>, X extends Exception> extends AbstractTest<ID,E,X> {

    protected final TestRestTemplate restTemplate;
    protected final String url;

    public AbstractRestTest(TestRestTemplate restTemplate, String url, JpaRepository<E, ID> repository){
        super(repository);
        this.restTemplate=restTemplate;
        this.url =url;
    }

    @Override
    protected Class<E> getClazz() {
        return null;
    }

    @Override
    protected List<ValidEntity<ID, E, X>> getValidEntities() {
        return null;
    }

    @Override
    protected List<InvalidEntity<ID, E, X>> getInvalidEntities() {
        return null;
    }

    @Override
    protected ParameterizedTypeReference<List<E>> getParametrizedTypeReference() {
        return null;
    }

    @Override
    protected List<E> getTestData() {
        return null;
    }

    @Override
    protected void test(){
        beforeTest();
        restTest();
        afterTest();
    }


    protected void restTest(){
        validEntities.forEach(e->{
            E entity =insertEntity(e.getEntity());
            findEntity(entity);
            e.onUpdate.accept(entity);
            updateEntity(entity) ;
            deleteEntity(entity);
        });

    }


    protected E insertEntity(E e) {
        ResponseEntity<E> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(e), getClazz());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        E body = responseEntity.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertNotNull(body.getId());

        E restored = this.repository.getReferenceById(body.getId());
        Assertions.assertNotNull(restored);
        Assertions.assertEquals(restored, body);

        return body;
    }


    protected void findEntity(E e) {
        ResponseEntity<E> response =this.restTemplate.exchange(url+"/"+e.getId(),HttpMethod.GET, new HttpEntity<>(e), getClazz() );

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        E body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertNotNull(body.getId());
        Assertions.assertEquals(e, body);

    }


    protected void updateEntity(E e){
        ResponseEntity<E> response  = this.restTemplate.exchange(url+"/"+e.getId(),HttpMethod.DELETE, new HttpEntity<>(e), getClazz());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        E body = response.getBody();
        Assertions.assertNotNull(body.getId());
        Assertions.assertEquals(e, body);
    }

    protected void deleteEntity(E e) {
        ResponseEntity<Void> response = this.restTemplate.exchange(url+"/"+e.getId(), HttpMethod.DELETE, new HttpEntity<>(e), Void.class );
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        repository.findById(e.getId()).ifPresent(d-> {throw new RuntimeException("%s is not deleted");});
    }

}
