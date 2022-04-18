import axios from "axios";
import {
  GET_ERRORS,
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
} from "./types";

export const createProjectTask =
  (projectIdentifier, projectTask, history) => async (dispatch) => {
    try {
      await axios.post(`/api/backlog/${projectIdentifier}`, projectTask);
      history.push(`/projectBoard/${projectIdentifier}`);
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

export const getBacklog = (projectIdentifier) => async (dispatch) => {
  const res = await axios.get(`/api/backlog/${projectIdentifier}`);
  dispatch({
    type: GET_BACKLOG,
    payload: res.data,
  });
};
