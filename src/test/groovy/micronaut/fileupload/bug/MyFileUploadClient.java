package micronaut.fileupload.bug;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;

/**
 * @author Silvio Wangler
 */
@Client("/")
public interface MyFileUploadClient {

    @Post(produces = MediaType.MULTIPART_FORM_DATA)
    String uploadFile(@Body MultipartBody file);
}
