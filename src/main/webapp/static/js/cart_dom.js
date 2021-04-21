import {dataHandler} from "./data_handler.js";
import {dom} from "./dom.js"

let cart_dom = {

    init: function () {
        this.addListeners()
    },
    addListeners: function () {
        dom.addEditQuantityListener()
        dom.addCheckOutButtonListeners()
        dom.addCancelButtonListener()
    }
}

window.onload = function() {
    cart_dom.init();
}