import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import models.TipsCoupon;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;

import static org.fest.assertions.Assertions.*;

public class TipsCouponTest {

    public static FakeApplication application;

    public TipsCoupon tipsCoupon;

    public static String createDdl = "";
    public static String dropDdl = "";

    //TODO move this code to an interface to avoid boilerplate code
    @BeforeClass
    public static void setup() throws Exception {
        application = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(application);

        String evolutionContent = FileUtils.readFileToString(
                application.getWrappedApplication().getFile("conf/evolutions/default/1.sql"));

        // Splitting the String to get Create & Drop DDL
        String[] splittedEvolutionContent = evolutionContent.split("# --- !Ups");
        String[] upsDowns = splittedEvolutionContent[1].split("# --- !Downs");
        createDdl = upsDowns[0];
        dropDdl = upsDowns[1];

    }

    @Before
    public void createCleanDb(){
        Ebean.execute(Ebean.createCallableSql(createDdl));
    }

    @Test
    public void createNewCouponTest(){
        tipsCoupon = new TipsCoupon();
        tipsCoupon.productId = 1L;
        tipsCoupon.productType = 2L;
        tipsCoupon.save();
        Long id = tipsCoupon.getId();

        TipsCoupon savedTipsCoupon = tipsCoupon.find(id);
        assertThat(savedTipsCoupon.getId()).isEqualTo(id);
        assertThat(savedTipsCoupon.productType).isEqualTo(tipsCoupon.productType);
        assertThat(savedTipsCoupon.productType).isNotEqualTo(100L);
    }

    @AfterClass
    public static void stopApplication(){
        Helpers.stop(application);
    }
}
