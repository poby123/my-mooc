"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const App_1 = __importDefault(require("./App"));
const app = new App_1.default().application;
app.listen(3001, () => {
    console.log('Server listening on port 3001');
});
//# sourceMappingURL=index.js.map