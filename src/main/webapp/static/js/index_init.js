import {dom} from "./dom.js";

// This function is to initialize the application

let index_init = {

    init() {
        this.addListeners()
    },

    addListeners() {
        dom.addSortListeners()
        dom.addCartButtonListeners()
        dom.addCheckCartButtonListener()
    }
}

window.onload = function () {
    index_init.init();
};