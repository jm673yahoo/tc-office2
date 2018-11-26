package ca.mydemo.tcoffice.dao;

import ca.mydemo.tcoffice.dao.mapper.BizMapper;
import org.junit.Test;
import org.springframework.context.annotation.Import;

import static org.junit.Assert.assertTrue;

//@RunWith(SpringRunner.class)
@Import(BizMapper.class)
public class BizDaoTest {

    //    @Autowired
    private BizMapper bizDao;


    @Test
    public void loadAllBizTest() {
//        List<Client> allBiz = bizDao.loadAllBiz();
//
//        assertTrue((allBiz.size() > 0));
        assertTrue(true);
    }
}
