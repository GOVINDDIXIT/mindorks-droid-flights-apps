package com.govinddixit.todonotesapp.clicklisteners

import com.govinddixit.todonotesapp.model.Notes

interface ItemClickListeners {
    fun onClick(notes: Notes?)
}