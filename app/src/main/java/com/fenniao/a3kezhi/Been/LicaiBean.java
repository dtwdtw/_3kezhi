package com.fenniao.a3kezhi.Been;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class LicaiBean {

    /**
     * name : licai
     * bannerList : [{"name":"first","imgUrl":"http://www.baidu.com/img.jpg","linkUrl":"http://www.baidu.com"},{"name":"first","imgUrl":"http://www.baidu.com/img.jpg","linkUrl":"http://www.baidu.com"}]
     * menuList : [{"name":"新手指引","icon":"http://www.google.com"},{"name":"新手指引","icon":"http://www.google.com"}]
     * classfied : [{"titleName":"新手专区","icon":"http://logo.com","descripe":"新人高预期收益","itemList":[{"name":"安E计划","rateOfReturn":12,"descripe":"历史年化收益率","longth":"1个月","longthDescripe":"期限"},{"name":"安E计划","rateOfReturn":12,"descripe":"历史年化收益率","longth":"1个月","longthDescripe":"期限"}]},{"titleName":"新手专区","icon":"http://logo.com","descripe":"新人高预期收益","itemList":[{"name":"安E计划","rateOfReturn":12,"descripe":"历史年化收益率","longth":"1个月","longthDescripe":"期限"}]}]
     */

    private String name;
    private List<BannerListBean> bannerList;
    private List<MenuItemBean> menuList;
    private List<ClassfiedBean> classfied;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<MenuItemBean> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuItemBean> menuList) {
        this.menuList = menuList;
    }

    public List<ClassfiedBean> getClassfied() {
        return classfied;
    }

    public void setClassfied(List<ClassfiedBean> classfied) {
        this.classfied = classfied;
    }

    public static class BannerListBean {
        /**
         * name : first
         * imgUrl : http://www.baidu.com/img.jpg
         * linkUrl : http://www.baidu.com
         */

        private String name;
        private String imgUrl;
        private String linkUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }
    }

    public static class MenuItemBean {
        /**
         * name : 新手指引
         * icon : http://www.google.com
         */

        private String name;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class ClassfiedBean {
        /**
         * titleName : 新手专区
         * icon : http://logo.com
         * descripe : 新人高预期收益
         * itemList : [{"name":"安E计划","rateOfReturn":12,"descripe":"历史年化收益率","longth":"1个月","longthDescripe":"期限"},{"name":"安E计划","rateOfReturn":12,"descripe":"历史年化收益率","longth":"1个月","longthDescripe":"期限"}]
         */

        private String titleName;
        private String icon;
        private String descripe;
        private List<ItemListBean> itemList;

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescripe() {
            return descripe;
        }

        public void setDescripe(String descripe) {
            this.descripe = descripe;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            /**
             * name : 安E计划
             * rateOfReturn : 12
             * descripe : 历史年化收益率
             * longth : 1个月
             * longthDescripe : 期限
             */

            private String name;
            private int rateOfReturn;
            private String descripe;
            private String longth;
            private String longthDescripe;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRateOfReturn() {
                return rateOfReturn;
            }

            public void setRateOfReturn(int rateOfReturn) {
                this.rateOfReturn = rateOfReturn;
            }

            public String getDescripe() {
                return descripe;
            }

            public void setDescripe(String descripe) {
                this.descripe = descripe;
            }

            public String getLongth() {
                return longth;
            }

            public void setLongth(String longth) {
                this.longth = longth;
            }

            public String getLongthDescripe() {
                return longthDescripe;
            }

            public void setLongthDescripe(String longthDescripe) {
                this.longthDescripe = longthDescripe;
            }
        }
    }
}
