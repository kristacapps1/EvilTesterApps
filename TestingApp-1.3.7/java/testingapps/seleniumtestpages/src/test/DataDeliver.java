    /* Written by Krista Capps 2/4/2020 */

import java.util.HashMap;
import java.util.Map;

public class DataDeliver {
    Map<String,String> urls;

    DataDeliver(){
        urls = new HashMap<String,String>();
        initUrls();
    }

    private void initUrls(){
        urls.put("http://localhost:4567/styled/basic-web-page-test.html","A paragraph of text");
        urls.put("http://localhost:4567/styled/attributes-test.html","Element Attributes Examples");
        urls.put("http://localhost:4567/find_by_playground.php","nested para text");
        //TODO add all
    }

    public Map<String,String> getUrls(){
        return urls;
    }

    /* TODO add other data elements for micro app/games interaction*/

}
