import {dataHandler} from "./data_handler.js";
import {dom} from "./dom.js"

let cart_init = {

    init: function () {
        this.addListeners()
    },
    addListeners: function () {
        dom.addEditQuantityListener()
        dom.addCheckOutButtonListeners()
        dom.addCancelButtonListener()
        dom.addCheckBoxListener()
    }
}

window.onload = function() {
    cart_init.init();
}