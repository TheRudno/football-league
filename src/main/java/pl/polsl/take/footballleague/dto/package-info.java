@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type = LocalDate.class,
        value = LocalDateXmlAdapter.class)
})
package pl.polsl.take.footballleague.dto;

import pl.polsl.take.footballleague.utils.LocalDateXmlAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;
