/**
*  iCamera Manager (Connect)
*
*  Copyright 2016 bconrad0321
*  Parent/Child SmartApp based on Ben Lebson and Patrick Stuart's Generic Video Camera SmartApp
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions and limitations under the License.
*
*/
definition(
    name: "iCamera Manager (Connect)",
    namespace: "bconrad0321",
    author: "bconrad0321",
    description: "This smartapp installs the iCamera Manager (Connect) App so you can add multiple Sercomm IP cameras",
    category: "Safety & Security",
    iconUrl: "https://www.heralduk.com/wp-content/uploads/2015/02/imgres2.png",
    iconX2Url: "https://www.heralduk.com/wp-content/uploads/2015/02/imgres2.png",
    iconX3Url: "https://www.heralduk.com/wp-content/uploads/2015/02/imgres2.png",
    singleInstance: true)


preferences {
    page(name: "mainPage", title: "Existing Cameras", install: true, uninstall: true) {
        if(state?.installed) {
            section("Add a New Camera") {
                app "iCamera Child", "bconrad0321", "iCamera Child", title: "New Fixed IP Camera", page: "mainPage", multiple: true, install: true
                app "iCamera PTZ Child", "bconrad0321", "iCamera PTZ Child", title: "New PTZ IP Camera", page: "mainPage", multiple: true, install: true
            }
        } else {
            section("Initial Install") {
                paragraph "This smartapp installs the iCamera Manager (Connect) App so you can add multiple IP cameras. Click 'Done' then go to smartapps in the flyout menu and add new cameras or edit existing cameras."
            }
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"

    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"

    unsubscribe()
    initialize()
}

def initialize() {
    state.installed = true
}