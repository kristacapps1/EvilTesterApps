package com.javafortesters.pulp.ActionTest;

import com.google.gson.Gson;
import com.javafortesters.pulp.api.DomainToEntityConvertor;
import com.javafortesters.pulp.api.EntityResponse;
import com.javafortesters.pulp.api.actions.BookActions;
import com.javafortesters.pulp.domain.groupings.PulpData;
import com.javafortesters.pulp.domain.objects.PulpBook;
import com.javafortesters.pulp.reader.PulpDataPopulator;
import com.javafortesters.pulp.reader.forseries.SavageReader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookActionTest {
    PulpData books;
    SavageReader reader;
    PulpDataPopulator populator;
    PulpBook mockBook;
    PulpBook realBook;
    BookActions bookAction;
    DomainToEntityConvertor converter;

    /* Action tests are all performed on the list of books so we can initialize here*/
    @Before
    public void init(){
        books = new PulpData();
        populator = new PulpDataPopulator(books);
        reader = new SavageReader("/data/pulp/doc_savage_test.csv");
        populator.populateFrom(reader);
        converter = new DomainToEntityConvertor(books);
        bookAction = new BookActions(books, converter, "/apps/pulp/api");
        realBook = books.books().findByName("The Angry Canary");

    }

    @Test
    public void canCreateReplace(){
        String bookid = "1";
        /* NOTE: Would actually create test json files for bodies/responses and create a class for
         * data processing as I did for SeleniumTestPages but for now I just threw these in here */
        String bodyjson =   "{\"authorIndexNames\":[\"1\",\"2\"],\"seriesIndexName\":\"1\",\"title\":\"Testers Guide\""
                            +",\"seriesId\":\"Jul / Aug, 1948\",\"publicationYear\":1948,\"publisherIndexName\":\"1\",\""
                            +"houseAuthorIndexName\":\"3\"}";
        String bodyResponse = "{\"data\":{\"books\":[{\"id\":\"1\",\"title\":\"Testers Guide\",\"publicationYear\":1948,\""
                              +"seriesId\":\"Jul / Aug, 1948\",\"authors\":[{\"id\":\"1\",\"name\":\"Lester Dent\"},{\"id\""
                              +":\"2\",\"name\":\"Will Murray\"}],\"series\":{\"id\":\"1\",\"name\":\"Doc Savage\"},\"publisher\""
                              +":{\"id\":\"1\",\"name\":\"Street And Smith\"}}]},\"logs\":{\"amended\":{\"books\":[{\"id\":\"1\"}]}}}";
        String contentType = "json";
        /* The below fails because it cannot find the publisher, author, or series,
        *  Which I suspect is a bug related to looking them up by IndexName */
        EntityResponse responseEntity = bookAction.createReplace(bookid, bodyjson, contentType, "");
        Assert.assertEquals(HttpStatus.OK_200,responseEntity.getStatusCode());
        Assert.assertEquals(bodyResponse,responseEntity.getResponseBody());
    }

    @Test
    public void cantCreateReplaceExisting(){
        String bookid = realBook.getId();
        String bodyjson =  new Gson().toJson(realBook);
        String contentType = "json";
        EntityResponse responseEntity = bookAction.createReplace(bookid, bodyjson, contentType, "");
        Assert.assertNotEquals(HttpStatus.OK_200,responseEntity.getStatusCode());
    }

    @Test
    public void canCreateAmend(){
        String bookid = realBook.getId();
        String bodyjson =  new Gson().toJson(realBook);
        String contentType = "json";
        EntityResponse responseEntity = bookAction.createAmend(bodyjson, contentType, "");
        Assert.assertEquals(HttpStatus.OK_200,responseEntity.getStatusCode());
        // TODO test body
    }

    @Test
    public void canPatchAmend(){
        String bookid = realBook.getId();
        String bodyjson =  new Gson().toJson(realBook);
        String contentType = "json";
        EntityResponse responseEntity = bookAction.patchAmend(bookid, bodyjson, contentType, "");
        Assert.assertEquals(HttpStatus.OK_200,responseEntity.getStatusCode());
        // TODO test body
    }
}
