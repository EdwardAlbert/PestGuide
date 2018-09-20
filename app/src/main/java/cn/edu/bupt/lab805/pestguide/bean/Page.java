package cn.edu.bupt.lab805.pestguide.bean;

import java.util.List;

/**
 * Created by zby on 2018/4/23.
 */

@lombok.Data
public class Page<T> {

    private List<T> rows;
    private Long total;
    private Integer pageNumber;
    private Integer pageSize;
    private String searchProperty;
    private String searchValue;
    private String orderProperty;
    private String orderDirection;
    private List orders;
    private Integer totalPages;

}
