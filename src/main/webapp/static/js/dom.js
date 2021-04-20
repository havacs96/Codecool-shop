import {dataHandler} from "./data_handler.js";


export let dom = {

    init: function () {
        this.addListeners()
    },
    addListeners: function () {
        dom.addSortListeners()
        dom.addCartButtonListeners()
        dom.addCheckCartButtonListener()
    },
    addSortListeners: function (){
        let options = document.querySelectorAll("select")
        for (let option of options) {
            option.addEventListener("change", (e) => {
                dom.changeProducts(e);
            })
        }
    },
    addCartButtonListeners() {
        let addToCartButtons = document.querySelectorAll(".add-to-cart")
        for (let addToCartButton of addToCartButtons) {
            addToCartButton.addEventListener("click", (e) => {
                dom.addToCart(e);
            })
        }
    },
    changeProducts: function (e) {
        dom.loadProduct(e.currentTarget.selectedOptions[0].dataset.sort,
            e.currentTarget.selectedOptions[0].dataset.id)
    },
    loadProduct: function (sortBy, id) {
        dataHandler.getProducts(sortBy, id, function (product) {
            dom.showProducts(product)
        })
    },
    showProducts: function (products) {
        let contentBody = document.querySelector("#products")
        contentBody.innerHTML = ""
        let cards = ""
        for (let product of products) {
            let card = `
            <div class="card">
                <img class="" src="/static/img/product_${product.id}.jpg" alt="" />
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success add-to-cart" data-id=${product.id}>Add to cart</a>
                    </div>
                </div>
            </div>`
            cards += card
        }
        contentBody.innerHTML = `<div class="col col-sm-12 col-md-6 col-lg-4">${cards}</div>`
        dom.addCartButtonListeners()
    },
    addToCart: function (e) {
        dataHandler.addProductToCart(e.currentTarget.dataset.id, function (cartSize){
            dom.changeCounter(cartSize);
        })
    },
    changeCounter: function (cartSize) {
        document.querySelector(".shopping-cart").innerHTML = cartSize
    },
    addCheckCartButtonListener() {
        let cartIcon = document.querySelector(".iconify.cart")
        let cartModal = document.querySelector(".modal")
        console.log(cartIcon)
        cartIcon.addEventListener("mouseenter", (e) =>{
            cartModal.style.display = "block";
        })
        cartIcon.addEventListener("mouseleave", (e) =>{
            cartModal.style.display = "none";
        })
    }
}