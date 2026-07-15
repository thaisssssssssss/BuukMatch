import "./styles/FileUploader.css"
import { useState } from "react";

function FileUploader({onFileSelect}) {  


    function handleFile(event){
        const selectedFile = event.target.files[0]
        onFileSelect(selectedFile)
    }

    
    return (
        <div className="file-uploader-pai">
            <input type="file" name="file" onChange={handleFile}>
            </input>    
        </div>
    )
}

export default FileUploader