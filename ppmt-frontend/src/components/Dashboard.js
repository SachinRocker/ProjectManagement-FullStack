import React, { Component } from 'react'
import CreateProjectButton from './Project/CreateProjectButton'
import ProjectItem from './Project/ProjectItem'
import projectItem from "./Project/ProjectItem"
import * as actions from "../store/Actions/index"
import {connect} from "react-redux"

class Dashboard extends Component {
     
    componentDidMount() {
        this.props.getLoadedProjects();
        
    }
    setLoadedProjects = () => {
       return this.props.loadedProjects.map(project => {
               return (<ProjectItem key={project.projectIdentifier}
                   projectName={project.projectName}
                   projectDescription={project.description}
                   id={project.projectIdentifier}
                   projectToDelete={this.projectDeleteHandler}
                   />)
            })
    }

    setProjectToUpdateHandler = (projectId) => {
        console.log("setProjectToUpdateHandler::",projectId)
         this.props.getProjectToUpdate(projectId)
    }
    projectDeleteHandler = (projectId) => {
        console.log("projectDeleteHandler::",projectId)
        this.props.getProjectToDelete(projectId)
    }
    render() {
        let projectItems = null;
        if (this.props.loadedProjects != null) {
            projectItems = this.setLoadedProjects();
        }
        return (
    <div className="projects">
    <div className="container">
        <div className="row">
            <div className="col-md-12">
                <h1 className="display-4 text-center">Projects</h1>
                <br />
               <CreateProjectButton/>
                <br />
                <hr />
                {projectItems}
            </div>
        </div>
    </div>
</div>

                
           
        )
    }
}

const mapStateToProps = state => {
    return {
        loadedProjects : state.projectReducers.projects
    }
}
const mapDispatchToProps = dispatch => {
    return {
        getLoadedProjects: () => dispatch(actions.loadProject()),
        getProjectToDelete: (projectId)=> dispatch(actions.deleteProject(projectId)),
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(Dashboard);
