package yu.mthgh123.booksmall.controller.vo;

import java.io.Serializable;

/**
 * 首页配置商品VO
 */
public class BooksMallIndexConfigGoodsVO implements Serializable {

    private Long goodsId;

    private String goodsName;

    private String author;

    private String goodsCoverImg;

    private Integer sellingPrice;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String goodsIntro) {
        this.author = goodsIntro;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
