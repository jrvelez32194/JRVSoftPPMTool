import { GET_ERRORS, GET_PROJECTS } from "../actions/types";

const initialState = {};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;
    case GET_PROJECTS:
      return { ...state, projects: action.payload };
    default:
      return state;
  }
}
