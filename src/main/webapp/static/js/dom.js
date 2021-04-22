import {dataHandler} from "./data_handler.js";


export let dom = {

    addSortListeners: function () {
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
    addCheckCartButtonListener() {
        let cartIcon = document.querySelector(".iconify.cart")
        let cartModal = document.querySelector(".modal")
        cartIcon.addEventListener("mouseenter", (e) => {
            cartModal.style.display = "block";
        })
        cartIcon.addEventListener("mouseleave", (e) => {
            cartModal.style.display = "none";
        })
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
         <div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src="/static/img/product_${product.id}.jpg" alt="" />
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success add-to-cart" data-id=${product.id}>Add to cart</a>
                    </div>
                </div>
            </div>
         </div>`
            cards += card
        }
        contentBody.innerHTML = `${cards}`
        dom.addCartButtonListeners()
    },
    addToCart: function (e) {
        dataHandler.addProductToCart(e.currentTarget.dataset.id, function (cartSize) {
            dom.changeCounter(cartSize);
            dom.loadCart();
        })
    },
    changeCounter: function (cartSize) {
        document.querySelector(".shopping-cart").innerHTML = cartSize
    },
    loadCart: function () {
        dataHandler.getCartItems(function (items) {
            dom.updateCart(items)
        })
    },
    updateCart(items) {
        let tableData = document.querySelector(".table-data")
        tableData.innerHTML = ''
        let listItems = "";
        for (let item of items) {
            let sum = item.quantity * item.defaultPrice;
            let listItem = `<tr data-id="${item.id}">
                    <td>${item.name}</td>
                    <td class="quantity">${item.quantity}</td>
                    <td>${item.defaultPrice} ${item.defaultCurrency}</td>
                    <td>${sum} ${item.defaultCurrency}</td>
                    </tr>`
            listItems += listItem
        }
        tableData.innerHTML = `
                <tr>
                    <th>Name:</th>
                    <th>Quantity:</th>
                    <th>Price:</th>
                    <th>Total price:</th>
                </tr>
                ${listItems}`;
        dom.addEditQuantityListener()
    },
    addEditQuantityListener() {
        let quantityFields = document.querySelectorAll(".quantity")
        for (let quantityField of quantityFields) {
            quantityField.addEventListener("click", dom.changeQuantityListener)
        }
    },
    changeQuantityListener(e) {
        e.currentTarget.removeEventListener('click', dom.changeQuantityListener)
        let oldQuantity = e.currentTarget.innerHTML
        e.currentTarget.innerHTML = ''
        let inputField = `<input type="text" class="changed-quantity" value="${oldQuantity}" data-oldquantity="${oldQuantity}">`
        e.currentTarget.insertAdjacentHTML("beforeend", inputField);
        e.currentTarget.addEventListener("keydown", dom.saveNewQuantity)
    },
    saveNewQuantity(e) {
        let changedQuantity = document.querySelector('.changed-quantity');
        if (e.key === "Enter") {
            if (!isNaN(changedQuantity.value)) {
                e.currentTarget.removeEventListener("keydown", dom.saveNewQuantity)
                e.currentTarget.innerHTML = changedQuantity.value
                e.currentTarget.addEventListener('click', dom.changeQuantityListener)
                let currentId = JSON.parse(e.currentTarget.parentNode.dataset["id"])
                dataHandler.changeQuantity(currentId, changedQuantity.value, dom.loadCart);
            } else {
                e.currentTarget.removeEventListener("keydown", dom.saveNewQuantity)
                e.currentTarget.innerHTML = changedQuantity.dataset.oldquantity
                e.currentTarget.addEventListener('click', dom.changeQuantityListener)
            }
        }
    },
    addCheckOutButtonListeners() {
        let checkOutButton = document.querySelector("#checkout-button")
        let checkOutModal = document.querySelector("#checkOutModal")
        checkOutButton.addEventListener("click", (e) => {
            checkOutModal.style.display = "block"
        })
    },
    addCancelButtonListener() {
        let cancelButton = document.querySelector(".cancel")
        let checkOutModal = document.querySelector("#checkOutModal")
        cancelButton.addEventListener("click", (e) => {
            checkOutModal.style.display = "none"
        })
    },
    addCheckBoxListener() {
        let checkbox = document.querySelector("#checkbox")
        let shippingForm = document.querySelector(".shipping-address")
        let inputFields = shippingForm.querySelectorAll("input")
        checkbox.addEventListener("change", ()=> {
            if (checkbox.checked){
                shippingForm.classList.add("hide")
                for (let inputField of inputFields) {
                    inputField.required = false
                }
            } else {
                shippingForm.classList.remove("hide")
                for (let inputField of inputFields) {
                    inputField.required = true
                }
            }
        })
    }
}