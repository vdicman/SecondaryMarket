package bean;

import java.util.List;

/**
 * Created by wangshouli on 17-5-7.
 */
public class Page {
    private int totalpage;   //一共的页数
    private  int pagesize=3;  //每页的记录数
    private int totalrecord;  //记录总数
    private  int pagenum;     //当前页码
    private List list;
    private int startpage;
    private int endpage;
    private  int startindex;   //当前页记录的开始索引

    public Page(int pagenum, int totalrecord) {
        this.pagenum = pagenum;
        this.totalrecord=totalrecord;
        this.totalpage=(this.totalrecord+this.pagesize-1)/this.pagesize;
        this.startindex=(this.pagenum-1)*pagesize;
        if(this.totalpage<=3){
            this.startpage=1;
            this.endpage=this.totalpage;
        }else {
            this.startpage=pagenum-1;
            this.endpage=pagenum+1;
            if(this.startpage<1){
                this.startpage=1;
                this.endpage=3;
            }
            if(this.endpage > this.totalpage){
                this.endpage=this.totalpage;
                this.startpage=this.totalpage-2;
            }
        }
    }

    public int getStartindex() {
        return startindex;
    }

    public void setStartindex(int startindex) {
        this.startindex = startindex;
    }

    public int getEndpage() {

        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    public int getStartpage() {

        return startpage;
    }

    public void setStartpage(int startpage) {
        this.startpage = startpage;
    }

    public List getList() {

        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPagenum() {

        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getTotalrecord() {

        return totalrecord;
    }

    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
    }

    public int getPagesize() {

        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalpage() {

        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
