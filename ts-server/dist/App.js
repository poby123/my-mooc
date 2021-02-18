"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const RestController_1 = __importDefault(require("./Controller/RestController"));
class App {
    constructor() {
        this.application = express_1.default();
        this.application.use('/rest', new RestController_1.default().router);
        this.router();
    }
    router() {
        this.application.get('/', (req, res) => {
            res.send('Hello!');
        });
    }
}
exports.default = App;
//# sourceMappingURL=App.js.map