import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectReducer from "./projectReducer";
import reducer from "./reducer";

export default combineReducers({
  errors: errorReducer,
  project: projectReducer,
});
