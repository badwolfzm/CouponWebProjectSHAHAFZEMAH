


Option Explicit
Private Sub Worksheet_Change(ByVal Target As Range)
    'Call run
    Dim GII As Integer, GI As String, Dayy As Range, Weekk As Range, Monthh As Range, Yearr As Range, unt As Integer
    
    If (Not (inSomething)) Then
        inSomething = True
        unt = Range("E1").Cells(1, 1).End(xlDown).Row
        'GII = Target.Cells(1, 1).Row
        
        
        'If (Not Application.Intersect(Range("G2:J" & unt), Target)) Then
         For GII = 2 To unt
            GI = GII
            Set Dayy = Worksheets("??????1").Range("A" & GI).Cells(1, 1)
            Set Sum = Worksheets("??????1").Range("E" & GI).Cells(1, 1)
            Set baseSalery = Worksheets("??????1").Range("F" & GI).Cells(1, 1)
            Set A100 = Worksheets("??????1").Range("G" & GI).Cells(1, 1)
            Set A100S = Worksheets("??????1").Range("H" & GI).Cells(1, 1)
            Set A125 = Worksheets("??????1").Range("I" & GI).Cells(1, 1)
            Set A125S = Worksheets("??????1").Range("I" & GI).Cells(1, 1)
            Set A150 = Worksheets("??????1").Range("K" & GI).Cells(1, 1)
            Set A150S = Worksheets("??????1").Range("L" & GI).Cells(1, 1)
            Set A175 = Worksheets("??????1").Range("M" & GI).Cells(1, 1)
            Set A175S = Worksheets("??????1").Range("N" & GI).Cells(1, 1)
            Set A200 = Worksheets("??????1").Range("O" & GI).Cells(1, 1)
            Set A200S = Worksheets("??????1").Range("P" & GI).Cells(1, 1)
            Set isSpaciel = Worksheets("??????1").Range("Q" & GI).Cells(1, 1)
            Set totalAmm = Worksheets("??????1").Range("R" & GI).Cells(1, 1)
            If (Not (IsEmpty(Sum))) Then
                Weekk.Value = Dayy.Value * 7
                Monthh.Value = Dayy * 30.42
                Yearr.Value = Dayy * 365
                Dayy.Value = Weekk.Value / 7
            ElseIf ((Not (Weekk.Value / 7 = Dayy.Value)) And ((Monthh.Value / 30.42 = Dayy.Value)) And (Yearr.Value / 365 = Dayy.Value)) Then
                Dayy.Value = Weekk.Value / 7
                Monthh.Value = Dayy * 30.42
                Yearr.Value = Dayy * 365
                Weekk.Value = Dayy.Value * 7
            ElseIf ((Not (Monthh.Value / 30.42 = Dayy.Value)) And (Weekk.Value / 7 = Dayy.Value) And (Yearr.Value / 365 = Dayy.Value)) Then
                Dayy.Value = Monthh.Value / 30.42
                Yearr.Value = Dayy * 365
                Weekk.Value = Dayy.Value * 7
                Monthh.Value = Dayy * 30.42
            ElseIf ((Not (Yearr.Value / 365 = Dayy.Value)) And ((Monthh.Value / 30.42 = Dayy.Value)) And (Weekk.Value / 7 = Dayy.Value)) Then
                Dayy.Value = Yearr.Value / 365
                Monthh.Value = Dayy * 30.42
                Weekk.Value = Dayy.Value * 7
                Yearr.Value = Dayy * 365
            Else
                Yearr.Value = Dayy.Value * 365
                Monthh.Value = Dayy.Value * 30.42
                Weekk.Value = Dayy.Value * 7
                Dayy.Value = Weekk.Value / 7
            End If
        'End If
        Next GII
        inSomething = False
        
    End If
    
End Sub
