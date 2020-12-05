import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem'
import projectItem from "./Project/ProjectItem"

export default class Dashboard extends Component {
    render() {
        return (
            <div >
                <h1 className="alert alert-warning">Welcome To Dashboard</h1>
                <ProjectItem/>
                
            </div>
        )
    }
}
