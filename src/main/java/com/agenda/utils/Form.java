package com.agenda.utils;

import java.util.List;

public record Form(String action,String method,List<Field> fields) {

}
