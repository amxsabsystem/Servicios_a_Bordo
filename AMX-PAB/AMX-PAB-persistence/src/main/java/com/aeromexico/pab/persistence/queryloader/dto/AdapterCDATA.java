package com.aeromexico.pab.persistence.queryloader.dto;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Alfredo Estrada
 */
public class AdapterCDATA extends XmlAdapter<String, String> {

    @Override
    public String marshal(String arg0) throws Exception {
        return "<![CDATA[" + arg0 + "]]>";
    }
    @Override
    public String unmarshal(String arg0) throws Exception {
        return arg0;
    }

}