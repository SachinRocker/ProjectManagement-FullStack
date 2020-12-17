import React, { Component } from 'react'
import * as actions from "../../store/Actions/index";
import { connect } from "react-redux"
import errorReducer from "../../store/reducers/errorReducer"
import classnames from "classnames"
class AddProject extends Component {
  
    state={
        projectName: "",
        projectIdentifier: "",
        description: "",
        startDate: "",
        endDate: "",
        disableIdentifier:false,
        errors: {}
    }

    componentDidMount() {
        console.log("componentDidMount")
        
        if (this.props.match.path === "/updateProject/:id") {
            
            const projectId = this.props.match.params.id;
            console.log("props, ",this.props," projectId::",projectId);
            this.props.getProjectUpdate(projectId);
            

            
            
        } else {
            this.setState({
                disableIdentifier:false,
            })
        }
        
       
    }
    componentDidUpdate(prevState) {
        if (this.props.error != this.state.errors) {
            console.log(" next::",this.props.error)
            this.setState({ errors : this.props.error });
            
        }
        if (this.props.projectToUpdate.projectIdentifier !== this.state.projectIdentifier
         && this.props.match.path === "/updateProject/:id") {
            console.log("updatedproject::", this.props.projectToUpdate);
            const projectData = { ...this.props.projectToUpdate };
            console.log("projectTo update::" + projectData, " ",projectData.projectName);
            
            
            this.setState({

                ...this.state,
                projectName:projectData.projectName,
                projectIdentifier: projectData.projectIdentifier,
                description: projectData.description,
                startDate: projectData.startDate,
                endDate: projectData.endDate,
                disableIdentifier: true,
                
            })
        }

    }
    
   onInputChangeHandler = () => {
            console.log(event.target.name, " ", event.target.value);
             this.setState({
                 [event.target.name]: event.target.value
                })
             
    }
    
    formSubmitHandler = () => {

        event.preventDefault();
        const formData = {  projectName: this.state.projectName,
                            projectIdentifier: this.state.projectIdentifier,
                            description:this.state.description,
                            startDate: this.state.startDate,
                            endDate: this.state.endDate
                        }
        console.log("submitted data::", formData)
        console.log("props ", this.props);
        switch (this.props.match.path)
        {
            case "/addProject":
                console.log("path matched::", this.props.match.path);
                this.props.onCreateProject(formData, this.props.history);
                break;
            case "/updateProject/:id":
                this.props.onUpdateProject(formData, this.props.history);
                break;
            default:
                break;

                

        }
        
    }


   
    render() {
        const { errors } = this.state;
        
        return (
            <div className="project">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Create / Edit Project form</h5>
                    <hr />
                    <form onSubmit={this.formSubmitHandler}>
                        <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg ", {
                                        "is-invalid":errors.projectName
                                    })} placeholder="Project Name"
                                        name="projectName" value={this.state.projectName} onChange={this.onInputChangeHandler} />
                                    { errors.projectName &&<div className="invalid-feedback">
                                        <p>{errors.projectName }</p>
                                     </div> }
                                </div>
                                
                            
                        <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg ", {
                                        "is-invalid":errors.projectIdentifier
                                    })} placeholder="Unique Project ID"
                                        name="projectIdentifier" value={this.state.projectIdentifier}
                                        disabled={this.state.disableIdentifier}
                                        onChange={this.onInputChangeHandler} />
                                    { errors.projectIdentifier &&<div className="invalid-feedback">
                                        <p>{errors.projectIdentifier }</p>
                                     </div> }
                                </div>

                       
                        <div className="form-group">
                                    <textarea className={classnames("form-control form-control-lg ", {
                                        "is-invalid":errors.description
                                    })} placeholder="Project Description"
                                        name="description" value={this.state.description} onChange={this.onInputChangeHandler}></textarea>
                                     { errors.description &&<div className="invalid-feedback">
                                        <p>{errors.description }</p>
                                     </div> }
                                </div>

                        <h6>Start Date</h6>
                        <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="startDate" value={this.state.startDate} onChange={this.onInputChangeHandler}/>
                        </div>
                        <h6>Estimated End Date</h6>
                        <div className="form-group">
                            <input type="date" className="form-control form-control-lg" name="endDate" value={this.state.endDate} onChange={this.onInputChangeHandler}/>
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        error: state.errorReducers.errors,
        loadedProjects: state.projectReducers.projects,
        projectToUpdate: state.projectReducers.project
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onCreateProject: (project, history) => dispatch(actions.createProject(project, history)),
        onUpdateProject: (project, history) => dispatch(actions.updateProject(project, history)),
        getProjectUpdate: (projectId)=> dispatch(actions.fetchProjectToUpdate(projectId))
    };
    
};
export default connect(mapStateToProps,mapDispatchToProps) (AddProject);
