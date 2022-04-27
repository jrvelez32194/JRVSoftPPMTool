import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";

export const createUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("/api/user/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
