import React, { Component } from 'react'
import * as actions from "../../store/Actions/index";
import { connect } from "react-redux"
import errorReducer from "../../store/reducers/errorReducer"

class AddProject extends Component {
  
    state={
        projectName: "",
        projectIdentifier: "",
        description: "",
        startDate: "",
        endDate: "",
        errors: {}
    }

    componentDidMount() {
        console.log("componentDidMount")
        
       
    }
    componentDidUpdate(prevState) {
        if (this.props.error != this.state.errors) {
            console.log(" next::",this.props.error)
            this.setState({ errors : this.props.error });
            
        }

    
        
    }
    // componentWillReceiveProps(nextProps) {
    //       if (nextProps.error) {
    //         console.log(" next::",this.props.error)
    //         this.setState({ errors : this.props.error });
            
    //     }

    // }
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
        this.props.onCreateProject(formData,this.props.history)
        
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
                                    <input type="text" className="form-control form-control-lg " placeholder="Project Name"
                                        name="projectName" value={this.state.projectName} onChange={this.onInputChangeHandler}/>
                                </div>
                                <p>{errors.projectName }</p>
                            
                        <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" placeholder="Unique Project ID"
                                         name="projectIdentifier" value={this.state.projectIdentifer } onChange={this.onInputChangeHandler}/>
                                </div>
                                 <p>{errors.projectIdentifier }</p>
                       
                        <div className="form-group">
                                    <textarea className="form-control form-control-lg" placeholder="Project Description"
                                        name="description" value={this.state.description} onChange={this.onInputChangeHandler}></textarea>
                                </div>
                                 <p>{errors.description }</p>

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
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        onCreateProject: (project, history) => dispatch(actions.createProject(project, history))
    };
    
};
export default connect(mapStateToProps,mapDispatchToProps) (AddProject);
