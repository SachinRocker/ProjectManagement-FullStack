import React, { Component } from 'react'
import { connect } from 'react-redux';
import { Link } from "react-router-dom"
import * as action from "../../store/Actions/index"
import { setProjectToUpdate } from './../../store/Actions/ProjectActions';

class ProjectItem extends Component {
    render() {
        return (
           
            <div className="container">
                    <div className="card card-body bg-light mb-3">
                        <div className="row">
                            <div className="col-2">
                                <span className="mx-auto">REACT</span>
                            </div>
                            <div className="col-lg-6 col-md-4 col-8">
                            <h3>{this.props.projectName }</h3>
                            <p>{ this.props.projectDescription}</p>
                            </div>
                            <div className="col-md-4 d-none d-lg-block">
                                <ul className="list-group">
                                    <a href="#">
                                        <li className="list-group-item board">
                                            <i className="fa fa-flag-checkered pr-1"> Project Board </i>
                                        </li>
                                    </a>
                                <Link to={`/updateProject/${this.props.id}`}>
                                        <li className="list-group-item update">
                                            <i className="fa fa-edit pr-1"> Update Project Info</i>
                                        </li>
                                    </Link>
                                    <a href="#" >
                                        <li className="list-group-item delete" onClick={this.props.projectToDelete.bind(this, this.props.id)}>
                                            <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                                        </li>
                                    </a>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
        )
    }
}

export default   ProjectItem; 