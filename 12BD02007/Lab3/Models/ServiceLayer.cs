using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Lab3;
using System.Data.Linq;

namespace Lab3.Models
{
    public class StudentServiceLayer:Lab3.Models.Lab3DataContext
    {
        public void delete(object entity){
            this.ExecuteDynamicDelete(entity);
        }

        public void insert(object entity)
        {
            this.ExecuteDynamicInsert(entity);
        }

        public void update(object entity)
        {
            this.ExecuteDynamicUpdate(entity);
        }

    }
}