package edu.global.ex.Dto;

public class RankDto {

    private String m_no;
    private String m_name;
    private String m_total;

    public RankDto() {}

    public RankDto(String m_no, String m_name, String m_total) {
        this.m_no = m_no;
        this.m_name = m_name;
        this.m_total = m_total;
    }

    public String getM_no() {
        return m_no;
    }

    public void setM_no(String m_no) {
        this.m_no = m_no;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_total() {
        return m_total;
    }

    public void setM_total(String m_total) {
        this.m_total = m_total;
    }
}
