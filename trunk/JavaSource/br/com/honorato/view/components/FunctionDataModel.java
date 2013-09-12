package br.com.honorato.view.components;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.honorato.dao.entity.Function;

public class FunctionDataModel extends ListDataModel<Function> implements SelectableDataModel<Function>  {

	public FunctionDataModel() {  
    }  
  
    public FunctionDataModel(List<Function> data) {  
        super(data);  
    }  
      
    @Override  
    public Function getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Function> functions = (List<Function>) getWrappedData();  
          
        for(Function function : functions) {  
            if(function.getIdModule().equals(rowKey))  
                return function;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Function function) {  
        return function.getIdModule();  
    }  
}
