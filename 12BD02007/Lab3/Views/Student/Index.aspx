<%@ Page Title="" Language="C#" MasterPageFile="~/Views/Shared/Site.Master" Inherits="System.Web.Mvc.ViewPage<IEnumerable<Lab3.Models.Student>>" %>

<asp:Content ID="Content1" ContentPlaceHolderID="TitleContent" runat="server">
	Index
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <form id="form1" action="/" method="post" runat="server">

    <h2>Index</h2>

    <table>
        <tr>
            <th></th>
            <th>
                ID
            </th>
            <th>
                Name
            </th>
            <th>
                GPA
            </th>
        </tr>

        <tr>
            <td>
                <input type="button" value="Create" onclick="document.forms[0].action = 'Student/Create';document.forms[0].submit();"/>
            </td>
            <td>

            </td>
            <td>
                <input type="text" name="CreateStudent" id="cs1"/>
            </td>
            <td>
                <input type="text" name="CreateStudent" id="cs2"/>
            </td>
        </tr>

    <% foreach (var item in Model)
       { %>
    
        <tr><td><input type="button" onclick="edit(this.id);" id="e<%: item.ID %>" value="edit"/> |<input type="button" onclick="delete_(this.id);" id="d<%: item.ID %>" value="delete"/></td><td><%: item.ID %></td><td><%: item.Name %></td><td><%: item.GPA %></td></tr>
    
    <% } %>

    </table>

</form>

</asp:Content>

