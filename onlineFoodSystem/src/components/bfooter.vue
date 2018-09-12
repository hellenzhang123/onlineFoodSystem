<template>
<div class="footer">
  <div class="tfooter">
      
    <div class="first">
        <div>
            <div class="top">用户帮助</div>
            <div class="behind"><a>我的客服</a></div>
        </div>
        <div>
            <div class="top">商务合作</div>
            <div class="behind"><a>我要开店</a></div>
            <div class="behind"><a>我要加盟</a></div>
            <div class="behind"><a>市场合作</a></div>

        </div>
        <div>
            <div class="top">关于我们</div>
            <div class="behind"><a>平台介绍</a></div>
            <div class="behind"><a>联系我们</a></div>
            <div class="behind"><a>规则中心</a></div>
        </div>
    </div>
    <div class="second">
       <div class="break">24小时客服热线</div>
       <div>8008208820</div>
    </div>
    <div class="third">
        <div><img src="/static/image/qrcode.png"  class="img"></div>
        <div class="rimg">
            <div class="break">扫码关注我们</div>
            <div>优惠多多</div>
        </div>
    </div>
  </div>
  <div class="bottom">
     Copyright ©2008-2018 ele.me, All Rights Reserved.互联网药品信息服务资格证书 编号：（沪）-经营性-2016-0011
  </div>
</div>
</template>

<style lang="stylus" rel="stylesheet/stylus">
.footer
  width: 1024px
  margin: 0 auto
  background: #ffffff
  .tfooter
    height:10rem
    display: flex
    .break
      margin-bottom: 1rem
    .first
      width: 50%
      height: 100%
      display: flex
      justify-content space-around
      .top
        padding: 1rem;
        font-size: 20px;
        color: black;
      .behind
        padding: 0.5rem 1rem;
        font-size: 16px;
        color: #f7f7f7;
    .second
      width: 25%
      height: 100%
      display: flex
      align-items: center
      justify-content: center
      flex-direction: column
   
        
    .third
      width: 25%
      height: 100%
      display: flex
      justify-content: space-around
      align-items: center
      .img
        width: 6rem
        height: 6rem
        border: 1px solid black
  
  .bottom
    padding: 1rem 0rem
    font-size: 14px
    display: flex
    justify-content: center

</style>
