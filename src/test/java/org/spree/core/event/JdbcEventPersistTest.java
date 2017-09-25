package org.spree.core.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JdbcEventPersistTest {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final GregorianCalendar START_DATE = new GregorianCalendar();
    public static final GregorianCalendar FINISH_DATE = new GregorianCalendar();
    public static final String EXT_ID = "extId";
    public static final String SYSTEM_ID = "systemId";

    @Autowired
    private JdbcTemplate jdbc;
    @Mock
    private Event event;

    private JdbcEvent sut;

    @Before
    public void setup() {
        initMocks(this);
        when(event.getId()).thenReturn(null);
        when(event.getName()).thenReturn(NAME);
        when(event.getDescription()).thenReturn(DESCRIPTION);
        when(event.getStartDate()).thenReturn(START_DATE);
        when(event.getFinishDate()).thenReturn(FINISH_DATE);
        when(event.getExtId()).thenReturn(EXT_ID);
        when(event.getSystemId()).thenReturn(SYSTEM_ID);
    }

    @Test
    @Transactional
    public void shouldSaveInJdbc() {
        sut = new JdbcEvent(jdbc, event);

        sut.save();

        Integer count = jdbc.queryForObject("select count(*) from events.EVENTS", Integer.class);
    }
}