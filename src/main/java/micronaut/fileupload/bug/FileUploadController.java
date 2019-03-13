package micronaut.fileupload.bug;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.reactivex.Single;
import org.reactivestreams.Publisher;

/**
 * @author Silvio Wangler
 */
@Controller
public class FileUploadController {

    @Post
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Single<String> uploadFile(Publisher<CompletedFileUpload> file) {
        return Single.fromPublisher(file)
                .map( part -> {
                    if (part.getContentType().isPresent()) {

                        MediaType mediaType = part.getContentType().get();

                        if (mediaType.getCharset().isPresent()) {
                            return String.format("Yes media type has a charset %s. All good :)", mediaType.getCharset().get());
                        }
                        else {
                            return "The part media type has no charset :(";
                        }
                    }
                    else {
                        return "No content type on part";
                    }
                });
    }

}
