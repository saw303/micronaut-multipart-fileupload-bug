package micronaut.fileupload.bug

import io.micronaut.http.MediaType
import io.micronaut.http.client.multipart.MultipartBody
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject
import java.nio.charset.Charset

/**
 * @author Silvio Wangler
 */
@MicronautTest
class FileUploadSpec extends Specification {

    @Inject
    MyFileUploadClient client

    void "The charset of a multipart post should be readable in a controller"() {

        given:
        String content = 'Müller;Möller;Maler;Malé'

        MultipartBody multipartBody = MultipartBody
                .builder()
                .addPart("file", 'test.csv', new MediaType("text/csv; charset=ISO-8859-1"), content.getBytes(Charset.forName('ISO-8859-1')))
                .build()

        when:
        String s = client.uploadFile(multipartBody)

        then:
        s == 'Yes media type has a charset ISO-8859-1. All good :)'
    }
}
