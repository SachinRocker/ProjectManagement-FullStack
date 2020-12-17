import { GET_PROJECT,GET_PROJECTID,DELETE_PROJECT } from "../Actions/ActionTypes"


const initialState = {
    projects: [],
    project:{}
}

const getProjects = (state, action) => {
    const projectList = [...action.loadedProjects];
    console.log(" projectList ",projectList)
    
    return {
        ...state,
        projects: projectList
    }

}

const getProjectTobeUpdated = (state, action) => {
    if (state.projects !== null) {
        const projectList = [...state.projects]
        console.log(" projectList ", projectList)
        const projectToUpdate = projectList.filter(pro => action.projectId == pro.projectIdentifier)[0];
        console.log("getProjectTobeUpdated ", projectToUpdate);
    
    
        return {
            ...state,
            projects: projectList,
            project: projectToUpdate
        }
    }
}
const getProjectForUpdate = (state, action) => {
    return {
        ...state,
        project: action.project
    }
}

const deleteSelectedProject = (state, action) => {
    
    const remainingProjects = state.projects.
        filter(project => project.projectIdentifier !== action.projectId);
    
    return {
        ...state,
        projects: remainingProjects,
        project:{}
    }
    
}

const reducer = (state = initialState, action) => {
    
    switch (action.type) {
        case GET_PROJECT:
            return getProjects(state, action);
         case GET_PROJECTID:
            return getProjectForUpdate(state, action);
         case DELETE_PROJECT:
            return deleteSelectedProject(state, action);
            
        default:
            return state;
    }
    
}
export default reducer;