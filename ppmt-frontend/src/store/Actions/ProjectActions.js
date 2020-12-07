import { GET_ERRORS } from "../Actions/ActionTypes"
import axios from "axios";

//
export const setCreateProjectError = (error) => {
    return {
        type: GET_ERRORS,
        error:error,
    }
}
//creating a project
export const createProject = (project, history) => {
    
    return (dispatch) => {
        axios.post("http://localhost:8080/project", project)
            .then((res) => {
                console.log("res", res);
              history.push("/dashboard");

            })
            .catch((err => {
               
                ;console.log(err.response)
                dispatch(setCreateProjectError(err.response.data));
            }))
        
        
    }
    
}



