<%--
  Created by IntelliJ IDEA.
  User: yale
  Date: 23/09/16
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>

    <style>
        p{
            font-family: arial, sans-serif;
            font-size: 150%;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<p>
    Welcome ${sessionScope.user}! <br>

    <a id="button1" href="#">Show Info</a>
</p>
<div id="user_info_div">
    <table id="info_table">
        <tr>
            <th>Codename</th>
            <th>Company Name</th>
            <th>Address</th>
            <th>City</th>
            <th>Postal Code</th>
        </tr>
    </table>
</div><br>
<p>

    <a id="button2" href="#">Show Manifests</a>
<div id="manifest_div">
    <table id="manifests_table">
        <tr>
            <th>Manifest Code</th>
            <th>Manifest Date Created</th>
            <th>BL Date Created</th>
            <th>Driver</th>
            <th>BOL</th>
            <th>Date Signed</th>
            <th>Status</th>
            <th>R-number</th>
        </tr>
    </table>
</div><br>

<div id="bl_div">
    <table id="bl_detail_table">
        <tr>
            <th>bl_weight</th>
            <th>bl_unm</th>
            <th>bl_pieces</th>
            <th>bl_location</th>
            <th>bl_loc_adr</th>
            <th>bl_city_prov</th>
            <th>bl_postal</th>
            <th>bl_load</th>
            <th>bl_load_status</th>
            <th>bl_sheet</th>
            <th>bl_sheet_date</th>
        </tr>
    </table>
</div><br>

<div id="imaging">
    <form action="upload" method="post" enctype="multipart/form-data">
        <table border = "0">
            <tr>
                <td>User Name: </td>
                <td><input type="text" name="user" size="50"/></td>
            </tr>
            <tr>
                <td>Select an image to upload: </td>
                <td><input type="file" name="photo" size="50"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Upload">
                </td>
            </tr>
        </table>
    </form>
</div>
</p>

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    $( document ).ready(function() {
        console.log( "ready!" );
        $( "#user_info_div" ).hide();
        $( "#manifest_div" ).hide();
        $( "#bl_detail_table" ).hide();

        $( "#button1" ).click(function() {
            console.log("button pressed");
            $( "#user_info_div" ).show();
        });
        $( "#button2" ).click(function() {
            console.log("button pressed");
            $( "#manifest_div" ).show();
        });

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/user_info",
            data: {
                uname: "${sessionScope.user}"
            },
            success: function(result){
                //alert(result);
                appendCsInfoToTable(result);

            },
            error: function(exception){
                alert("error has occurred:    "+JSON.stringify(exception));
            }

        });
    });


    function appendCsInfoToTable(result){
        var resultJson = JSON.parse(result);
        var csInfoJson = resultJson["customerInfo"];

        var cs_name = csInfoJson["cs_name"];
        var cs_adr = csInfoJson["cs_adr"];
        var cs_code = csInfoJson["cs_code"];
        var cs_city = csInfoJson["cs_city"];
        var cs_postal = csInfoJson["cs_postal"];

//        alert(result);

        $('#info_table').find('tbody')
                .append($('<tr><td>'+cs_code+'</td><td>'+cs_name+'</td><td>'+cs_adr+'</td><td>'+cs_city+'</td><td>'+cs_postal+'</td></tr>'));

        getManifestInfo(cs_code);
    }

    function getManifestInfo(code){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/manifest_info",
            data: {
                code: code
            },
            success: function(result){
                //alert(result);
                appendManifestInfoToTable(result);

            },
            error: function(exception){
                alert("error has occurred:    "+JSON.stringify(exception));
            }

        });
    }
    function appendManifestInfoToTable(result){
        var resultJson = JSON.parse(result);
        var manifestInfoJson = resultJson["manifestInfo"];

        var bl_manifest = manifestInfoJson["bl_manifest"];
        var bl_man_date = manifestInfoJson["bl_man_date"];
        var bl_create_date = manifestInfoJson["bl_create_date"];
        var bl_man_job = manifestInfoJson["bl_man_job"];
        var bl_bol = manifestInfoJson["bl_bol"];
        var bl_signed_date = manifestInfoJson["bl_signed_date"];
        var bl_detailed_status = manifestInfoJson["bl_detailed_status"];
        var r_number = manifestInfoJson["r_number"];

        $('#manifests_table').find('tbody')
                .append($('<tr><td>'+bl_manifest+'</td><td>'+bl_man_date+'</td><td>'+bl_create_date+'</td><td>'
                        +bl_man_job+'</td><td>'+bl_bol+'</td><td>'+bl_signed_date+'</td><td>'+bl_detailed_status+'</td><td>'+r_number+'</td></tr>'));


        //alert("info:  ");
    }

</script>
</body>
</html>
