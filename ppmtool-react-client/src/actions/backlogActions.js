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
  try {
    const res = await axios.get(`/api/backlog/${projectIdentifier}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data,
    });
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

export const getProjectTask =
  (backlog_id, pt_id, history) => async (dispatch) => {
    try {
      const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
      dispatch({
        type: GET_PROJECT_TASK,
        payload: res.data,
      });
      dispatch({
        type: GET_ERRORS,
        payload: {},
      });
    } catch (err) {
      history.push("/dashboard");
    }
  };

export const updateProjectTask =
  (backlog_id, pt_id, project_task, history) => async (dispatch) => {
    try {
      await axios.patch(`/api/backlog/${backlog_id}/${pt_id}`, project_task);
      history.push(`/projectBoard/${backlog_id}`);
      dispatch({
        type: GET_ERRORS,
        payload: {},
      });
    } catch (err) {
      console.log(err.response.data);
      dispatch({
        type: GET_ERRORS,
        payload: err.response.data,
      });
    }
  };
export const deleteProjectTask = (backlog_id, pt_id) => async (dispatch) => {
  if (
    window.confirm("Are you sure you want to delete selected project task?")
  ) {
    await axios.delete(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: pt_id,
    });
  }
};
