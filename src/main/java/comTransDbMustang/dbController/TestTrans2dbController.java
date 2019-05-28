package comTransDbMustang.dbController;

import comTransDbMustang.dbModel.User;
import comTransDbMustang.dbService.DbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tranDb2")
public class TestTrans2dbController {
    @Autowired
    private DbTestService dbTestService;

    @GetMapping("/import/{name}")
    public void testTransConsole (@PathVariable("name") String name) {
        User u = new User();
        u.setName(name);
        Integer finalCount = dbTestService.doTest2DBInsert(u);
        log.info("----------------------》"+finalCount);
    }
}
